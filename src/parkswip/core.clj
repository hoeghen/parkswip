(ns bilswap.core
  (:gen-class)
  (:require [datomic.api :as d]))


(def db-uri "datomic:mem://bilswap")

(d/create-database db-uri)
(def conn (d/connect db-uri))

(def schema [{:db/ident       :address/city
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :address/street
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :address/number
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}])
(def data
  [
   {:address/city "dragør" :address/street "raagaards alle" :address/number 15}
   {:address/city "dragør" :address/street "raagaards alle" :address/number 14}])

(def all '[:find ?e
           :where
           [?e :address/city "dragør"]])

(comment

  (d/transact conn schema)
  (d/transact conn data)
  (def db (d/db conn))
  (d/q all db))












