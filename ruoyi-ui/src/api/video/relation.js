import request from '@/utils/request'

// 查询视频与标签关联列表
export function listRelation(query) {
  return request({
    url: '/video/relation/list',
    method: 'get',
    params: query
  })
}

// 查询视频与标签关联详细
export function getRelation(videoId) {
  return request({
    url: '/video/relation/' + videoId,
    method: 'get'
  })
}

// 新增视频与标签关联
export function addRelation(data) {
  return request({
    url: '/video/relation',
    method: 'post',
    data: data
  })
}

// 修改视频与标签关联
export function updateRelation(data) {
  return request({
    url: '/video/relation',
    method: 'put',
    data: data
  })
}

// 删除视频与标签关联
export function delRelation(videoId) {
  return request({
    url: '/video/relation/' + videoId,
    method: 'delete'
  })
}
