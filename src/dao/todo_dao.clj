(ns dao.todo_dao
    (:use ring.util.response)
    (:require
        [clojure.java.jdbc :as sql]))

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
    (response
    (into [] (sql/query db ["select * from todos order by id desc"]))))