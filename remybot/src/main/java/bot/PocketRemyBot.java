package bot;

import bot.models.Ingredient;
import bot.models.Recipe;
import bot.models.User;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.vdurmont.emoji.*;

import java.util.ArrayList;
import java.util.List;

public class PocketRemyBot extends TelegramLongPollingBot {
    
    //example Start
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();

        try {
            botapi.registerBot(new PocketRemyBot());
        } catch (TelegramApiException e) {
            //write error massage
        }
    }

    //example answer TEXT
    public void sendTextMsg(Message msg, String text) {
        try {
            execute(new SendMessage().setChatId(msg.getChatId()).setText(text));
        } catch (Exception e) {
//            LOG.error("Can't send Text message: ", e);
        }
    }

    public void sendTextMsg(CallbackQuery query, String text) {
        try {
            execute(new SendMessage().setChatId(query.getMessage().getChatId()).setText(text));
        } catch (Exception e) {
//            LOG.error("Can't send Text message: ", e);
        }
    }

    public void sendTextMsg(Message msg, String text, InlineKeyboardMarkup ikm) {
        try {
            execute(new SendMessage().setChatId(msg.getChatId()).setText(text).setReplyMarkup(ikm));
        } catch (Exception e) {
//            LOG.error("Can't send Text message: ", e);
        }
    }

    //example answer GIF
    public void sendGIFMsg(Message msg, String gif) {
        try {
            execute(new SendDocument().setChatId(msg.getChatId()).setDocument(gif));
        } catch (Exception e) {
//            LOG.error("Can't send GIF message: ", e);
        }
    }

    //example answer IMAGE
    public void sendImageMsg(Message msg, String image) {
        try {
            execute(new SendPhoto().setChatId(msg.getChatId()).setPhoto(image));
        } catch (Exception e) {
//            LOG.error("Can't send Photo message: ", e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        List<Recipe> recipes = Recipe.getAllPecipes();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String m = message.getText();

            if(m.toLowerCase().contains("привет"))
            {
                sendTextMsg(message, "Привет, я Реми!");
            }
            else {
                if (m.toLowerCase().contains(" реми")) {
                    sendTextMsg(message, EmojiParser.parseToUnicode("Да, это я :wink:"));
                }
                else {
                    if (m.toLowerCase().equals("реми")) {
                        sendTextMsg(message, EmojiParser.parseToUnicode("Фасоль :notes:"));
                        sendTextMsg(message, "Ба-дум-тсс");
                    }
                }
            }
            if(m.toLowerCase().contains("пользователи"))
            {
                List<User> users = User.getAllUsers();
                for (User user : users)
                    sendTextMsg(message, user.toString());
            }

            if(m.toLowerCase().contains("рецепты"))
            {
                ChooseRecipe(message);
            }
        }
        if(update.hasCallbackQuery()) {
            CallbackQuery cq = update.getCallbackQuery();
            int rec = Integer.parseInt(cq.getData());
            Recipe chosen = recipes.get(rec);
            List<Ingredient> ingredients = chosen.getIngredients();
            String[] steps = chosen.getSteps();
            String ingrs = "Ингредиенты:\n";
            for(Ingredient ingr : ingredients)
            {
                ingrs += ingr.toString() + "\n";
            }
            sendTextMsg(cq, ingrs);
            for (int i = 0; i < steps.length; i++) {
                sendTextMsg(cq, (i + 1) + ":" + steps[i]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ChooseRecipe(Message message)
    {
        List<Recipe> recipes = Recipe.getAllPecipes();
        String res = "";
        for(int i = 1; i <= recipes.size(); i++)
        {
            res += i + ": " + recipes.get(i-1).toString() + "\n";
        }
        sendTextMsg(message, res, setInline(recipes.size()));
    }

    private InlineKeyboardMarkup setInline(int amount) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> recipes = new ArrayList<>();
        for(int i = 0; i < amount; i++)
            recipes.add(new InlineKeyboardButton().setText(String.valueOf(i + 1)).setCallbackData(String.valueOf(i)));
        buttons.add(recipes);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }

    @Override
    public String getBotUsername() {
        return "PocketRemyBot";
    }

    @Override
    public String getBotToken() {
        return "694300181:AAH1LiNJ5zocrK7PuCCMmjn4z8drZ_9ZeuA";
    }
}