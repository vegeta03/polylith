(ns polylith.clj.core.shell.candidate.custom-cmd)

(defn auto-complete [workspace]
  (mapv #(-> % second :auto-complete)
        (-> workspace :settings :commands)))
