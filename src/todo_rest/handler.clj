(ns todo-rest.handler
  (:use ring.util.response)
  (:use cheshire.core)
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.java.jdbc :as sql]
            [ring.middleware.json :as middleware]
            [api.todo_api :as api]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(let [db-host "localhost"
  db-port 5432
  db-name "todos_clj"]

(def db {:classname "org.postgresql.Driver" ; must be in classpath
        :subprotocol "postgresql"
        :subname (str "//" db-host ":" db-port "/" db-name)
        ; Any additional keys are passed to the driver
        ; as driver-specific properties.
        :user "jasonlane"
        :password ""}))

(defn getTodos []
  (api/getTodos))

(defn createTodo [todo]
    (sql/insert! db :todos [:name] [(get todo "todo")])
    (getTodos))

(defn updateTodo [todo]
  (sql/update! db :todos {:name (get todo "name")} ["id = ?" (get todo "id")])
  (getTodos))

(defn deleteTodo [id]
  (sql/delete! db :todos ["id = ?" id])
  (getTodos))

(defroutes app-routes
  (GET "/" [] (getTodos))
  (POST "/" {body :body} (createTodo body))
  (PUT "/" {body :body} (updateTodo body))
  (DELETE "/" {body :body} (deleteTodo (get body "id")))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
  (middleware/wrap-json-body)
  (middleware/wrap-json-response)))


