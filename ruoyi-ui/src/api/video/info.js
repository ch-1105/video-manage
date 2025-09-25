import request from '@/utils/request'

// 查询视频文件信息列表
export function listVideo(query) {
  return request({
    url: '/videomanage/video/list',
    method: 'get',
    params: query
  })
}

// 扫描指定媒体库
export function scanVideo(libraryId, libraryPath) {
  const data = {
    libraryId,
    libraryPath
  }
  return request({
    url: '/videomanage/video/scan',
    method: 'post',
    data: data
  })
}
