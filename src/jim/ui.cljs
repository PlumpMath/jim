(ns jim.ui
  (:require-macros [sablono.core :refer [html]])
  (:require [jim.state :as state]))

(defn my-new-card [state]
  (html [:h1 {:on-click #(state/text! state "New value")}
          (state/text state)]))

(defn app
  "Render application"
  [state]
  (html [:div {} "APP"
          (my-new-card state)
          [:input {:type "button"
                   :on-click #(state/emit state/pipe [:click 1 1])
                   :value "Press me"}]
          [:input {:type "button"
                   :on-click #(state/emit state/pipe [:click-too 2 2])
                   :value "Press me too"}]]))
