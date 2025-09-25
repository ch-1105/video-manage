import request from '@/utils/request'

// 查询视频文件信息列表
export function listInfo(query) {
  return request({
    url: '/video/videoInfo/list',
    method: 'get',
    params: query
  })
}

// 查询视频文件信息详细
export function getInfo(videoId) {
  return request({
    url: '/video/videoInfo/' + videoId,
    method: 'get'
  })
}

// 新增视频文件信息
export function addInfo(data) {
  return request({
    url: '/video/videoInfo',
    method: 'post',
    data: data
  })
}

// 修改视频文件信息
export function updateInfo(data) {
  return request({
    url: '/video/videoInfo',
    method: 'put',
    data: data
  })
}

// 删除视频文件信息
export function delInfo(videoId) {
  return request({
    url: '/video/videoInfo/' + videoId,
    method: 'delete'
  })
}
