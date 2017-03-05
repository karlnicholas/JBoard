package jboard.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import jboard.model.Article;
import jboard.model.ArticleComment;

@Stateless
public class ArticleService {
	@Inject private EntityManager em;
	
	public List<Article> getArticles() {
		return em.createNamedQuery(Article.GET_ARTICLES_AND_COMMENTS, Article.class).getResultList();
	}

	public void postComment(Long openComment, String comment) {
		Article a = em.createNamedQuery(Article.GET_ARTICLE_BY_ID, Article.class).setParameter("id", openComment).getSingleResult();
		ArticleComment c = new ArticleComment(a);
		c.setComment(comment);
		c.setImportance(1);
		em.persist(c);
	}
}
