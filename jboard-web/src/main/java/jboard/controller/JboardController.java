/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    private Long openArticle;

    private List<Article> articleViewList;
    
    @PostConstruct
    public void postConstruct() {
    	openArticle = null;
    	articleViewList = articleService.getArticles();
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
	//TODO: does this belong in JsfUtils?
	public List<Integer> repeatNTimes(int count) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i=0; i < count; ++i) {
			result.add(i);
		}
		return result;
	}
}
