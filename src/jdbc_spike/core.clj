(ns jdbc-spike.core
  (:require [clojure.java.jdbc :as sql]))

(def db (or (System/getenv "DATABASE_URL") "postgres://localhost/mgorsuch"))

(defn migrate [db]
  (sql/with-connection db
    (sql/create-table :testing [:data :text])))

(defn destroy [db]
  (sql/with-connection db
    (sql/drop-table :testing)))

(defn get-data [db]
  (sql/with-connection db
    (sql/with-query-results rows
      ["SELECT * FROM testing"]
      (into [] rows))))

(defn insert-data [db data]
  (sql/with-connection db
    (sql/insert-records :testing {:data data})))
