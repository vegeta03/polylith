(ns polylith.clj.core.help.text-table.shell-commands
  (:require [polylith.clj.core.help.shared :as s]
            [polylith.clj.core.help.text-table.table :as table]))

(defn commands [cm]
  [{:command (str "switch-ws " (s/key "ARG" cm))
    :description "Switches to specified workspace."}
   {:command (str "tap [" (s/key "ARG" cm) "]")
    :description (str "Opens a portal window that outputs " (s/key "tap>" cm) " statements.")}
   {:command "exit"
    :description "Exits the shell."}
   {:command "quit"
    :description "Quits the shell."}])

(defn print-table [color-mode]
  (println (str "\n  From the shell:\n"))
  (table/print-table color-mode (commands color-mode)))
