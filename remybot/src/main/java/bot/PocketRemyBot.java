package bot;

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
    private void sendTextMsg(Message msg, String text) {
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

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String m = message.getText();

            if (m.toLowerCase().contains("привет")) {
                sendTextMsg(message, "Привет, я Реми!");
            } else {
                if (m.toLowerCase().contains(" реми")) {
                    sendTextMsg(message, EmojiParser.parseToUnicode("Да, это я :wink:"));
                } else {
                    if (m.toLowerCase().equals("реми")) {
                        sendTextMsg(message, EmojiParser.parseToUnicode("Фасоль :notes:"));
                        sendTextMsg(message, "Ба-дум-тсс");
                    }
                }
            }
        }
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