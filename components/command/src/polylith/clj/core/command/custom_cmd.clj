(ns polylith.clj.core.command.custom-cmd)

(defn execute [cmd args workspace]
  (if-let [execute-fn (-> workspace :settings :commands :execute-fn)]
    (execute-fn args workspace)
    (println "Couldn't find custom command '" cmd "'.")))
