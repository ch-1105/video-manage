import request from '@/utils/request'

// 查询本地文件夹列表
export function listLibrary(query) {
  return request({
    url: '/video/library/list',
    method: 'get',
    params: query
  })
}

// 查询本地文件夹详细
export function getLibrary(libraryId) {
  return request({
    url: '/video/library/' + libraryId,
    method: 'get'
  })
}

// 新增本地文件夹
export function addLibrary(data) {
  return request({
    url: '/video/library',
    method: 'post',
    data: data
  })
}

// 修改本地文件夹
export function updateLibrary(data) {
  return request({
    url: '/video/library',
    method: 'put',
    data: data
  })
}

// 删除本地文件夹
export function delLibrary(libraryId) {
  return request({
    url: '/video/library/' + libraryId,
    method: 'delete'
  })
}
