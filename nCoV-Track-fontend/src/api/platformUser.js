import request from './ajax/local'

export function queryPlatformUsername(params) {
  return request.post('/platformUser/platformUsername', params)
}

export function registerPlatformUser(params) {
  return request.post('/platformUser/registerPlatformUser', params)
}
