



export function makeUrlWithParams(url,params){

    let paramsForUrl=""
    let counter = 0

    for (const [key, value] of Object.entries(params)) {
        if(counter===0){
            paramsForUrl = paramsForUrl + "?" + key +"=" + value
            counter++;
        }else{
            paramsForUrl = paramsForUrl + "&" + key + "=" + value
        }
    }
    return url + paramsForUrl

}