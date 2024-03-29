(ns pc.views.content
  (:require [hiccup.core :as h]))

(defn layout [& content]
  [:html
   [:head
    [:title "Precursor - Mockups from the future"]]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"}]
   [:body
    ;;[:link.css-styles {:rel "stylesheet", :href "/css/bootstrap.min.css"}]
    ;;[:link.css-styles {:rel "stylesheet", :href "/css/styles.css"}]
    [:div.alerts-container]
    content
    (when true ;;(not= :prod #_(carica/config :env-name))
      ;;[:script (browser-connected-repl-js)]
      )]])

(defn app* []
  (layout
   [:input.history {:style "display:none;"}]
   [:div#player-container]
   [:div#app-container]
   [:div.debugger-container]
   [:div#app]
   [:link.css-styles {:rel "stylesheet", :href (str "/css/app.css?rand=" (Math/random))}]
   [:link {:rel "stylesheet" :href "https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400" :type "text/css"}]
   (if (= (System/getenv "PRODUCTION") "true")
     (list
      [:script {:type "text/javascript" :src "/js/vendor/react-0.10.0.js"}]
      [:script {:type "text/javascript" :src (str "/cljs/production/frontend.js?rand=" (Math/random))}])
     (if false
       [:script {:type "text/javascript" :src "/js/bin-debug/main.js"}]
       (list
        [:script {:type "text/javascript" :src "/js/vendor/react-0.10.0.js"}]
        [:script {:type "text/javascript" :src "/cljs/out/goog/base.js"}]
        [:script {:type "text/javascript" :src "/cljs/out/frontend-dev.js"}]
        [:script {:type "text/javascript"}
         "goog.require(\"frontend.core\");"])))))

(defn app []
  (h/html (app*)))
