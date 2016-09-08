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
                   :on-click #(state/emit state/pipe [:click state])
                   :value "Press me"}]
          [:input {:type "button"
                   :on-click #(state/emit state/pipe [:click-too state])
                   :value "Press me too"}]]))
