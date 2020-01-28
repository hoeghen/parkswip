(ns parkswip.model
  (:require
    [java-time :as jt]
    [common.spec-email :as e]
    [clojure.spec.alpha :as s]
    [clojure.string :as str]))


;; starttime should be before endtime
(defn start-before-end [m]
  (let [{start ::starttime end ::endtime} m]
    (jt/before?  (jt/instant start) (jt/instant end))))


(s/def ::email ::e/email)
;; Period for availability
(s/def ::weekday #{:monday :tuesday :wednesday :thursday :friday :saturday :sunday})
(s/def ::weekdays (s/coll-of ::weekday :distinct true))

(s/def ::starttime inst?)
(s/def ::endtime inst?)

(s/def ::period (s/and
                  (s/keys :req [::weekdays ::starttime ::endtime])
                  start-before-end))


;; Address of the parkingspot
(s/def ::vej string?)
(s/def ::number string?)
(s/def ::postcode string?)
(s/def ::address (s/keys :req [::vej ::number ::postcode]))

(s/def ::price float?)

; parking
(s/def ::parking (s/keys :req [::address ::price ::period]))
(s/def ::parkings (s/coll-of ::parking))
(s/def ::user (s/keys :req [::email ::parkings]))


(def user-generator (s/gen ::user))

;; define a string that holds the local namespace
(defonce this-na
         ;; define a string that holds the local namespace
         (str (:ns (meta #'user-generator))))

(defn get-all-specs []
  (let  [prefix (str ":" this-na)
         fl (filter #(str/starts-with? % prefix))
         specs ((comp keys s/registry))]
    (into [] fl specs)))

