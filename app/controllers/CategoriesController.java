package controllers;

import models.Categories;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static play.libs.Json.toJson;

public class CategoriesController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public CategoriesController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getCategories()
    {
        List<Categories> categories = (List<Categories>) jpaApi.em().createQuery("select categoryId, categoryName from Categories").getResultList();

        return ok(toJson(categories));
    }
    @Transactional(readOnly = true)
    public Result index()
    {
        List<Categories> categories = (List<Categories>) jpaApi.em().createQuery("select c from Categories c").getResultList();

        return ok(views.html.categories.render(categories));
    }
    @Transactional(readOnly = true)
    public Result newCategory()
    {
        return ok(views.html.newcategory.render());
    }

    @Transactional
    public Result addCategory()
    {
        Categories category = formFactory.form(Categories.class).bindFromRequest().get();
        jpaApi.em().persist(category);
        return redirect(routes.CategoriesController.index());
    }
    @Transactional(readOnly = true)
    public Result getPicture(Long iD)
    {
        Categories categories = (Categories) jpaApi.em().createQuery("select c from Categories c where categoryId = :Id").setParameter("Id", iD).getSingleResult();

        if (categories.picture == null)
        {
            return null;
        } else
        {
            return ok(categories.picture).as("image/bmp");

        }
    }
    @Transactional(readOnly = true)
    public Result editCategory(Long categoryId)
    {
        Categories category = (Categories)jpaApi.em().createQuery("select c from Categories c where categoryId = :id").setParameter("id", categoryId).getSingleResult();
        return ok(views.html.updateCategory.render(category));
    }

    @Transactional
    public Result updateCategory()
    {
        DynamicForm postedForm = formFactory.form().bindFromRequest();
        Long categoryId = new Long(postedForm.get("categoryId"));
        String categoryName = postedForm.get("categoryName");
        String description = postedForm.get("description");
        Categories category = (Categories)jpaApi.em().createQuery("select c from Categories c where categoryId = :id").setParameter("id", categoryId).getSingleResult();
        category.categoryName = categoryName;
        category.description = description;
        jpaApi.em().persist(category);
        return redirect(routes.CategoriesController.index());
    }


}




