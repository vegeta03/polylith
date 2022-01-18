(ns polylith.clj.core.command.ws-name
  (:require [polylith.clj.core.shell.interface.creator :as c]))

(defn spec [_]
  (c/single-txt "ws-name"))

(defn execute [args {:keys [name]}]
  (println name))
