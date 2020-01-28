(ns parkswip.core
  (:gen-class))




(def schema [{:db/ident       :address/city
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :address/street
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :address/number
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}])












