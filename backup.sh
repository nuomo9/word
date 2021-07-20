cd /c/Users/someone/word
# 备份7天
backupFile=$(date +%F)_word.sql
deleteFile=$(date -d -7days +%F)_word.sql
# 备份文件
mysqldump -h127.0.0.1 -P3306 -uroot -p12345678 -B -q -f --skip-extended-insert --complete-insert --skip-add-locks --skip-lock-tables --single-transaction --set-gtid-purged=OFF word >${backupFile}
# 删除过期文件
if [[ -f ${deleteFile} ]]; then
    rm -rf ${deleteFile}
fi