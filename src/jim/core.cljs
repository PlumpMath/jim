(ns jim.core
  (:require [sablono.core]
            [jim.cards.cards]
            [jim.state :as state]
            [jim.ui :as ui]))


(enable-console-print!)


(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (when-let [node (.getElementById js/document "main-app-area")]
    (state/process-pipe state/pipe)
    (add-watch state/app-state
               :render
               #(js/ReactDOM.render (ui/app state/app-state) node))
    (js/ReactDOM.render (ui/app state/app-state) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html
