/*
 * The MIT License (MIT)
 * Copyright (C) 2012 Jason Ish
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the Software), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED AS IS, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.spo.cms3.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//support like http://localhost:8089/pages/action/about_step_by_step
@Controller
@RequestMapping("/pages")
public class PagesController {
	   private static final Logger logger = LoggerFactory
	            .getLogger(PagesController.class);
    @RequestMapping("")
    public @ResponseBody String index() {
        return "This is the admin section.";
    }

    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String metrics(Locale locale, Model model) {
        logger.info("Welcome home! the client locale is " + locale.toString());
        

        return "cms1/home";
    }
    
    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public String login(Locale locale, Model model) {
        return "login1";
    }
    
    @RequestMapping(value = "/login1", method = RequestMethod.POST)
    public String loggedin(Locale locale, Model model) {
        return "admin/metrics";
    }
}
