Ember - JEE REST
=====

This project shows how to mix Ember.js and Java to create a very simple application using the DS.RESTAdapter from Ember Data library.


Other Technologies used
-----

This application depends on other technologies beyond the scope of Java. Therefore, I assume that you have this tools installed and know how to use them:
- Maven
- ruby
- RubyGems

Will be helpfull if you know something about tools that concatenate and minify or compress JavaScript and CSS assets like this one [rake-pipeline](https://github.com/livingsocial/rake-pipeline).


Building
--------

First, run `bundle` to install the dependencies necessary to compile the static assets (CSS, JS, HTML). The Assetfile is configured to output the files located on `app` directory to the `target/public/` directory.

Then execute `mvn jetty:run` to run the application on port 8080. This target executes the command `bundle exec rakep` at the validate phase, as configured in the `exec-maven-plugin` plugin section from pom.xml file.

Development
-----------

When you execute the command `bundle exec guard`, the `app` directory will be monitored and every file modification on this directory starts the rake-pipeline process. This process updates the `target/public/` directory and you can call <Cmd + R> or <Ctrl + R> to reload on the browser. This is useful when you need a fast development enviroment.

This is one of de advantages of use Ember with Java, because you don't need to restart you app while modifying only the views, and scripts.
