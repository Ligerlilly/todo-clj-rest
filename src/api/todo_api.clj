(ns api.todo_api
    (:require
        [dao.todo_dao :as dao]))

(defn getTodos []
    (dao/getTodos))

(defn createTodo [todo]
    (dao/createTodo todo))

(defn updateTodo [todo]
    (dao/updateTodo todo))

(defn deleteTodo [id]
    (dao/deleteTodo id))