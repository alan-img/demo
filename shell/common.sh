function s(){
  docker ps | grep $1 | grep -v pause | grep -v sidecar;
};

function to(){
  docker exec -it -u root -w / `docker ps | grep $1 | grep -v pause | grep -v sidecar | head -n 1 | awk '{print $1}'` /bin/bash;
};

function sk(){
  kubectl get pods -A -o wide -n cloudspace | grep $1;
};

function tok(){
  kubectl exec -it `kubectl get pods -n cloudspace | grep $1 | awk '{print $1}'` -n cloudspace -- /bin/bash;
};

function desk(){
  kubectl describe pod `kubectl get pods -n cloudspace | grep $1 | awk '{print $1}'` -n cloudspace;
};

function topk(){
  kubectl top pod -n cloudspace;
};

function monk() {
  kubectl get pods -w -o wide -n cloudspace | grep $1;
};

function logk() {
  kubectl logs -f  -n cloudspace `kubectl get pods -n cloudspace | grep $1 | awk '{print $1}'`;
};

function port() {
  netstat -nlpt | grep $1;
};

function proc() {
  ps -ef | grep `netstat -nlpt | grep $1 | awk '{print $7}' | head -n 1 | cut -d '/' -f 1`;
};

function root() {
    gosu root bash;
};