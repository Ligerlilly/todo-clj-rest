(ns todo-rest.handler
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.java.jdbc :as sql]
            [ring.middleware.json :as middleware]
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

(defn all []
  (response
  (into [] (sql/query db ["select * from todos order by id desc"]))))


(defroutes app-routes
  (GET "/" [] (all))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
  (middleware/wrap-json-body)
  (middleware/wrap-json-response)))


