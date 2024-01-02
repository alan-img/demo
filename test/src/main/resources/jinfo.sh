function jvm(){
  jinfo -flag NewRatio $(jps | grep $1 | awk '{print $1}');
  jinfo -flag SurvivorRatio $(jps | grep $1 | awk '{print $1}');

  eden=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $5}'`;
  eden_capacity=$(echo "scale=2; $eden / 1024" | bc);
  eden=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $6}'`;
  eden_usage=$(echo "scale=2; $eden / 1024" | bc);
  echo EDEN   capacity: $eden_capacity    usage: $eden_usage;

  from=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $1}'`;
  from_capacity=$(echo "scale=2; $from / 1024" | bc);
  from=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $3}'`;
  from_usage=$(echo "scale=2; $from / 1024" | bc);
  echo FROM   capacity: $from_capacity    usage: $from_usage;

  to=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $2}'`;
  to_capacity=$(echo "scale=2; $to / 1024" | bc);
  to=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $4}'`;
  to_usage=$(echo "scale=2; $to / 1024" | bc);
  echo TO   apacity: $to_capacity    usage: $to_usage;

  old=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $7}'`;
  old_capacity=$(echo "scale=2; $old / 1024" | bc);
  old=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $8}'`;
  old_usage=$(echo "scale=2; $old / 1024" | bc);
  echo OLD    capacity: $old_capacity   usage: $old_usage;

  metasapce=`jinfo -flag MetaspaceSize $(jps | grep $1 | awk '{print $1}') | cut -d '=' -f 2`;
  metaspace_capacity=$(echo "scale=2; $metasapce / 1024 / 1024" | bc);
  metasapce=`jstat -gc $(jps | grep $1 | awk '{print $1}') | tail -n 1 | awk '{print $10}'`
  metaspace_usage=$(echo "scale=2; $metasapce / 1024" | bc);
  echo metasapce    capacity: $metaspace_capacity   usage: $metaspace_usage;
}