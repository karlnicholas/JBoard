package jboard.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import jboard.model.Article;
import jboard.service.ArticleService;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class JboardController implements Serializable {

//    @Inject private FacesContext facesContext;
    @Inject private ArticleService articleService;
//    public static final String ACTION_JBOARD = "index.xhtml";
    private Long openArticle;
    private Long openComment;
    private String comment;

    private List<Article> articleViewList;
    
    @PostConstruct
    public void postConstruct() {
    	openArticle = null;
    	openComment = null;
    	articleViewList = articleService.getArticles();
    }
    
    public String postComment() {
    	if ( comment != null && !comment.isEmpty() ) {
    		openArticle = openComment;
    		articleService.postComment(openComment, comment);
        	articleViewList = articleService.getArticles();
    	}
		openComment = null;
		comment = null;
//    	return "/views/index.html";
    	return "";
    }

    public List<Article> getArticleViewList() {
    	return articleViewList;
    }
    
    public void openArticle(Long id) {
    	openArticle = id;
    }
    public void closeArticle() {
    	openArticle = null;
    }
    public boolean articleOpen(Long id) {
    	return (openArticle != null && openArticle.equals(id) );
    }

    public void openComment(Long id) {
    	openComment = id;
    	openArticle = id;
    }
    public void closeComment() {
    	openComment = null;
    }
    public boolean commentOpen(Long id) {
    	return (openComment != null && openComment.equals(id) );
    }
	//TODO: does this belong in JsfUtils?
	public List<Integer> repeatNTimes(int count) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i=0; i < count; ++i) {
			result.add(i);
		}
		return result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
