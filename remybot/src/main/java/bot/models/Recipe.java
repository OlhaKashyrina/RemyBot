package bot.models;

import bot.dao.ObjectDao;
import bot.dao.ParamDao;
import bot.entities.Param;
import bot.entities.Object;

import java.util.ArrayList;
import java.util.List;

import static bot.models.Constants.URL_OBJECTS;
import static bot.models.Constants.URL_PARAMS;

public class Recipe {
    private Long recipeId;
    private String recipeName;
    private String[] steps;
    private String website;

    private static ObjectDao objectDao = new ObjectDao();
    private static ParamDao paramDao = new ParamDao();
    private static List<Object> objects;
    private static List<Param> params;

    public Recipe(Long id, String name, String steps, String web)
    {
        recipeId = id;
        recipeName = name;
        this.steps = steps.split(";");
        website = web;
    }


    public static List<Recipe> getAllPecipes()
    {
        objects = objectDao.getAll(URL_OBJECTS);
        params = paramDao.getAll(URL_PARAMS);
        List<Recipe> recipes = new ArrayList<>();
        for(Object obj : objects)
        {
            if(obj.getObject_type_id() == 2)
            {
                Recipe recipe = new Recipe(obj.getObject_id(), obj.getName(), "Пока не знаю, как готовить :(", null);
                for(Param param : params)
                {
                    if(param.getAttr_id()==2 && param.getObject_id().equals(recipe.recipeId))
                        recipe.setSteps(param.getText_value());

                    if(param.getAttr_id()==3 && param.getObject_id().equals(recipe.recipeId))
                        recipe.setWebsite(param.getText_value());
                }
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public List<Ingredient> getIngredients()
    {
        List<Ingredient> ingredients = new ArrayList<>();
        List<IngredientsRecipes> irs = IngredientsRecipes.getAllIngredientsRecipes();
        for(IngredientsRecipes ir : irs)
        {
            if(ir.getRecipeId().equals(this.recipeId))
                ingredients.add(ir.getIngr());
        }
        return ingredients;
    }
    public void addRecipe(String[] ingredients, User user)
    {
        Object obj = new Object(this.recipeName, 2L);
        Object recipe = objectDao.insert(URL_OBJECTS, obj, Object.class);
        Param steps = new Param(stepsToString(), null, null, 2L, recipe.getObject_id());
        paramDao.insert(URL_PARAMS, steps, Param.class);
        Param website = new Param(this.website, null, null, 3L, recipe.getObject_id());
        paramDao.insert(URL_PARAMS, website, Param.class);

        List<Ingredient> allIngr = Ingredient.getAllIngredients();

        for(int i = 0; i < ingredients.length; i++)
        {
            Ingredient current = null;
            for(Ingredient ing : allIngr)
            {
                if(ing.getIngrName().toLowerCase().equals(ingredients[i].toLowerCase()))
                {
                    current = ing;
                    break;
                }
            }
            Long currId;
            if(current == null)
            {
                Object newIngr = new Object(ingredients[i], 3L);
                Object currIngr = objectDao.insert(URL_OBJECTS, newIngr, Object.class);
                currId = currIngr.getObject_id();
            }
            else
                currId = current.getIngrId();


            Object ir = new Object(null, 6L);
            Long irId = objectDao.insert(URL_OBJECTS, ir, Object.class).getObject_id();
            Param recId = new Param(null, recipe.getObject_id(), null, 10L, irId);
            Param ingrId = new Param(null, currId, null, 11L, irId);
            paramDao.insert(URL_PARAMS, recId, Param.class);
            paramDao.insert(URL_PARAMS, ingrId, Param.class);
        }

        Object ur = new Object(null, 5L);
        Long urId = objectDao.insert(URL_OBJECTS, ur, Object.class).getObject_id();
        Param recId = new Param(null, recipe.getObject_id(), null, 9L, urId);
        Param userId = new Param(null, user.getUserId(), null, 8L, urId);
        paramDao.insert(URL_PARAMS, recId, Param.class);
        paramDao.insert(URL_PARAMS, userId, Param.class);
    }


    public Long getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String[] getSteps() {
        return steps;
    }

    public String getWebsite() {
        return website;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setSteps(String steps) {
        this.steps = steps.split(";");
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String stepsToString()
    {
        String res = "";
        for(int i = 0; i < steps.length; i++)
        {
            if(i < steps.length-1)
                res += steps[i] + ";";
            else res += steps[i];
        }
        return res;
    }

    @Override
    public String toString() {
        return this.recipeName;
    }
}
