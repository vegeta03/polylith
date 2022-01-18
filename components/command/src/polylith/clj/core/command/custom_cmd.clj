(ns polylith.clj.core.command.custom-cmd
  (:require [polylith.clj.core.util.interface :as util]))

(defn execute-cmd [cmd-ns args workspace]
  (require cmd-ns)
  ((util/fn-var cmd-ns "execute") args workspace))

(defn execute [cmd args workspace]
  (let [commands (-> workspace :settings :commands)
        cmd-ns (second (util/find-first #(= cmd (first %)) commands))]
    (if cmd-ns
      (execute-cmd cmd-ns args workspace)
      (println "Couldn't find custom command '" cmd "'."))))
