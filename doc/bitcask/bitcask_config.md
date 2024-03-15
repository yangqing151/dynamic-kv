# bitcask支持的配置
> 参考地址：https://docs.riak.com/riak/kv/2.2.3/setup/planning/backend/bitcask/index.html
* 数据文件地址：
  * bitcask.data_root：./data/bitcask
* 启动时打开目录超时时间：
  * bitcask.sync.open_timeout：默认10s
  * 【暂不实现】
* 文件同步策略
  * 【暂不实现】
* 单个文件大小
  * bitcask.max_file_size：默认1GB
* hint是否包含crc校验值
  * bitcask.hintfile_checksums = strict
  * 【不实现】
* 合并策略
  * bitcask.merge.policy:[never|always|window]

* 合并触发配置
  * 碎片率阈值：
    * bitcask.merge.triggers.fragmentation:默认60，表示100个有60个无用的key就触发合并
  * 无用字节数：
    * bitcask.merge.triggers.dead_bytes: 默认值为512MB。表示无用的字节key超过512M就会触发合并


