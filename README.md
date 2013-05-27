flume-ng-hbase-sink
===================

customized flume-ng-hbase-sink

When I tried to sink the syslog source to hbase, the default SimpleAsyncHbaseEventSerializer doesn't write the header of log, so I think maybe it's a good idea to customized this serializer.
