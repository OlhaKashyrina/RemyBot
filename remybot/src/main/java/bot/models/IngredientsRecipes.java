package bot.models;

import bot.dao.ObjectDao;
import bot.dao.ParamDao;
import bot.entities.Param;
import bot.entities.Object;

import java.util.ArrayList;
import java.util.List;

import static bot.models.Constants.URL_OBJECTS;
import static bot.models.Constants.URL_PARAMS;

public class IngredientsRecipes {
    private Long irId;
    private Long ingrId;
    private Long recipeId;

    public IngredientsRecipes(Long irId, Long ingrId, Long recipeId)
    {
        this.irId = irId;
        this.ingrId = ingrId;
        this.recipeId = recipeId;
    }

    private static ObjectDao objectDao = new ObjectDao();
    private static ParamDao paramDao = new ParamDao();
    private static List<Object> objects;
    private static List<Param> params;

    public static List<IngredientsRecipes> getAllIngredientsRecipes()
    {
        objects = objectDao.getAll(URL_OBJECTS);
        params = paramDao.getAll(URL_PARAMS);
        List<IngredientsRecipes> irs = new ArrayList<>();
        for(Object obj : objects)
        {
            if(obj.getObject_type_id() == 6)
            {
                IngredientsRecipes ir = new IngredientsRecipes(obj.getObject_id(), null, null);
                for(Param param : params)
                {
                    if(param.getAttr_id()==11 && param.getObject_id().equals(ir.getIrId()))
                        ir.setIngrId(param.getNumber_value());

                    if(param.getAttr_id()==10 && param.getObject_id().equals(ir.getIrId()))
                        ir.setRecipeId(param.getNumber_value());
                }
                irs.add(ir);
            }
        }
        return irs;
    }

    public Long getIrId() {
        return irId;
    }

    public Long getIngrId() {
        return ingrId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setIrId(Long irId) {
        this.irId = irId;
    }

    public void setIngrId(Long ingrId) {
        this.ingrId = ingrId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Ingredient getIngr() {
        for(Ingredient ingr : Ingredient.getAllIngredients())
        {
            if(ingr.getIngrId().equals(this.getIngrId()))
                return ingr;
        }
        return null;
    }

    public Recipe getRecipe()
    {
        for(Recipe recipe : Recipe.getAllPecipes())
        {
            if(recipe.getRecipeId().equals(this.getRecipeId()))
                return recipe;
        }
        return null;
    }
}
