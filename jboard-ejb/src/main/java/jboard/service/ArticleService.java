package jboard.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import jboard.model.Article;

@Stateless
public class ArticleService {
	@Inject private EntityManager em;
	
	public List<Article> getArticles() {
		return em.createNamedQuery(Article.GET_ARTICLES_AND_COMMENTS, Article.class).getResultList();
	}
}
