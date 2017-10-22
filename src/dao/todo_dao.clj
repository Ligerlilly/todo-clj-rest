(ns dao.todo_dao
    (:use ring.util.response)
    (:require
        [clojure.java.jdbc :as sql]))

(def db (or (System/getenv "DATABASE_URL")
""))

(defn getTodos []
    (response
    (into [] (sql/query db ["select * from todos order by id desc"]))))

(defn createTodo [todo]
    (sql/insert! db :todos [:name] [(get todo "todo")])
    (getTodos))

(defn updateTodo [todo]
    (sql/update! db :todos {:name (get todo "name")} ["id = ?" (get todo "id")])
    (getTodos))

(defn deleteTodo [id]
    (println id)
    (sql/delete! db :todos ["id = ?" id])
        (getTodos))

