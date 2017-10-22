(ns api.todo_api
    (:require
        [dao.todo_dao :as dao]))

(defn getTodos []
    (dao/getTodos))