(ns frontend.routes
  (:require [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer close!]]
            [clojure.string :as str]
            [frontend.async :refer [put!]]
            [goog.events :as events]
            [frontend.utils.docs :as doc-utils]
            [frontend.utils :as utils :include-macros true]
            [secretary.core :as sec :include-macros true :refer [defroute]])
  (:require-macros [cljs.core.async.macros :as am :refer [go go-loop alt!]]))

(defn define-spec-routes! [nav-ch]
  (defroute trailing-slash #"(.+)/$" [path]
    (put! nav-ch [:navigate! {:path path :replace-token? true}]))
  (defroute v1-not-found "*" []
    (put! nav-ch [:error {:status 404}])))

(defn define-routes! [state]
  (let [nav-ch (get-in @state [:comms :nav])]
    (define-spec-routes! nav-ch)))
