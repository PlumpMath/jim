(ns jim.core
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true]
   [cljs.test :as test])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defcard first-card
  (sab/html [:div {:on-click #(js/alert "JIM")}
             [:h1 "This is NOT your first devcard!"]]))

(defcard new-card
  "### New Card"
  (fn [state]
    (sab/html
      [:h1 {:on-click #(swap! state assoc :text "New value")}
        (:text @state)]))
  {:text "Text"}
  {:inspect-data true
   :history true})

(defn toggle-todo [state row]
  (swap! state assoc-in [:data (.indexOf (get @state :data) row) :done] (not (get row :done))))

(defn draw-list [state]
  (sab/html
    [:table
     [:tbody
      (for [row (get @state :data)]
        [:tr {:key (get row :id)}
         [:td (get row :todo)]
         [:td [{:input
                :type "checkbox"
                :checked (get row :done)
                :name (str "checkbox-" (get row :id))
                :on-change #(toggle-todo state row)
                }]]])]])
  (defcard to-do-list
           "### TODO List"
           (fn [state]
             (draw-list state)
             )
           {
            :headers ["todo" "done"]
            :data    [
                      {:id 1 :todo "NRIC Card" :done false}
                      {:id 2 :todo "EP" :done false}
                      {:id 3 :todo "Shopping" :done true}]
            }
           {
            :inspect-data true
            :history      true
            }))

(deftest my-test
 (test/testing "equality" (test/is (= 1 1))))

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (js/React.render (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html
