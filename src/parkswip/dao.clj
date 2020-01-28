(ns parkswip.dao
  (:require
          [datomic.api :as d]
          [provisdom.spectomic.core :as spectomic]
          [parkswip.model :as m]
          [clojure.spec.gen.alpha :as gen]))


(def db-uri "datomic:mem://bilswap")
(d/delete-database db-uri)
(d/create-database db-uri)

(def conn (d/connect db-uri))

(def generated-schema (spectomic/datomic-schema (m/get-all-specs)))

(defn is-ref [m]
  (= (:db/valueType m) :db.type/ref))

(def schema-with-components (map #(assoc % :db/isComponent true) (filter is-ref generated-schema)))

(d/transact conn generated-schema)
(d/transact conn schema-with-components)




(def query-user '[:find ?e .
                  :in $ ?email
                  :where [?e :parkswip.model/email ?email]])

(defn get-user [email]
    (->>
      (d/q query-user (d/db conn) email)
      (d/pull (d/db conn) '[*])))

(defn save-user [user]
  (d/transact conn [user])
  (:parkswip.model/email user))


(comment
  (def r (gen/generate m/user-generator))
  (def e (save-user r))
  (def res (get-user e))
  (println res)
  (println "done"))



