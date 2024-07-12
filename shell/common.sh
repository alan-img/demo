namespace=cloudspace
function s() { docker ps | grep $1 | grep -v pause | grep -v sidecar; }
function to() { docker exec -it -u root -w / $(docker ps | grep $1 | grep -v pause | grep -v sidecar | head -n 1 | awk '{print $1}') /bin/bash; }
function sk() { kubectl -n ${namespace} get pods -A -o wide | grep $1; }
function tok() { kubectl -n ${namespace} exec -it $(kubectl -n ${namespace} get pods | grep $1 | awk '{print $1}') -- /bin/bash; }
function sa() { kubectl -n ${namespace} get po -o wide; }
function epod() { kubectl -n ${namespace} edit pod $1; }
function dpod() { kubectl -n ${namespace} delete po $1; }
function sdep() { kubectl -n ${namespace} get deploy | grep $1; }
function edep() { kubectl -n ${namespace} edit deploy $1; }
function ssts() { kubectl -n ${namespace} get sts | grep $1; }
function ests() { kubectl -n ${namespace} edit sts $1; }
function ssvc() { kubectl -n ${namespace} get svc | grep $1; }
function esvc() { kubectl -n ${namespace} edit svc $1; }
function watpod() { kubectl -n ${namespace} get pods -w -o wide | grep $1; }
function despod() { kubectl -n ${namespace} describe pod $(kubectl -n ${namespace} get pods | grep $1 | awk '{print $1}'); }
function toppod() { kubectl -n ${namespace} top pod; }
function logpod() { kubectl -n ${namespace} logs -f $(kubectl -n ${namespace} get pods | grep $1 | awk '{print $1}'); }
