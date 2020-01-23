(ns parkswip.model
  (:require
            [java-time :as jt]
            [common.spec-time :as t]
            [common.spec-email :as e]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))


;; starttime should be before endtime
(defn start-before-end [m]
  (let [{start :starttime end :endtime} m]
    (jt/before?  start end)))


;; Period for availability
(s/def ::weekday #{:monday :tuesday :wednesday :thursday :friday :saturday :sunday})
(s/def ::weekdays (s/coll-of ::weekday :distinct true))
(s/def ::starttime :common.spec-time/time)
(s/def ::endtime :common.spec-time/time)
(s/def ::period (s/and
                  (s/keys :req-un [::weekdays ::starttime ::endtime])
                  start-before-end))


;; Address of the parkingspot
(s/def ::vej string?)
(s/def ::number string?)
(s/def ::postcode string?)
(s/def ::address (s/keys :reg-un [::vej ::number ::postcode]))

(s/def ::price float?)

; parking
(s/def ::parking (s/keys :req-un [::address ::price ::period]))
(s/def ::parkings (s/coll-of ::parking))
(s/def ::user (s/keys :req-un [:common.spec-email/email ::parkings]))


