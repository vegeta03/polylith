(ns polylith.clj.core.command.ws-name
  (:require [polylith.clj.core.autocomplete.interface :as a]))

(defn spec [_]
  (a/single-txt "ws-name"))

(defn execute [args {:keys [name]}]
  (println name))
