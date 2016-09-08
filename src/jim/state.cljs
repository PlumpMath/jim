(ns jim.state
  (:require [cljs.core.async
              :refer [chan put! <!]])
  (:require-macros [cljs.core.async.macros :refer [go-loop]]))

(defonce app-state (atom {}))

(defn text [state]
  (get @state :text "DIV"))

(defn text! [state value]
  (swap! state assoc :text value))

(defonce pipe (chan pipe))

(defn emit [channel message]
  (put! channel message))

(defmulti process-event
  (fn [[event-type]] event-type))

(defmethod process-event :click
  [event]
  (text! (nth event 1) "You clicked!"))

(defmethod process-event :click-too
  [event]
  (text! (nth event 1) "You clicked the other one too!"))

; (defn process-event
;   [[event-type]]
;   (case event-type
;     :click (js/alert "click")
;     :click-too (js/alert "click too")
;     (println "NO DISPATCH FOR " (str event-type))))

(defn process-pipe [channel]
  (go-loop []
    (when-let [event (<! pipe)]
      (process-event event))
    (recur)))
