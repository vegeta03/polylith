(ns polylith.clj.core.help.text-table.shell-commands
  (:require [polylith.clj.core.help.shared :as s]
            [polylith.clj.core.help.text-table.table :as table]
            [polylith.clj.core.text-table.interface :as text-table]))

(defn shell-commands [cm]
  [{:command (str "switch-ws " (s/key "ARG" cm))
    :description "Switches to specified workspace."}
   {:command (str "tap [" (s/key "ARG" cm) "]")
    :description (str "Opens a portal window that outputs " (s/key "tap>" cm) " statements.")}
   {:command "exit"
    :description "Exits the shell."}
   {:command "quit"
    :description "Quits the shell."}])

(defn print-table [commands color-mode]
  (println (str "\n  From the shell:\n"))
  (let [shell-cmds (shell-commands color-mode)
        custom-cmds (mapv #(-> % second :help :overview) commands)
        all-commands (concat shell-cmds custom-cmds)
        table-all (table/table color-mode all-commands)
        custom-table (drop 4 table-all)
        shell-table (take 4 table-all)]
    (text-table/print-table custom-table)
    (println)
    (text-table/print-table shell-table)))

(comment
  (require '[dev.jocke :as dev])
  (def color-mode "dark")
  (def workspace dev/workspace)
  (def commands (-> workspace :settings :commands))
  (print-table commands "dark")
  #__)
