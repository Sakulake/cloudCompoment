local good = KEYS[1]
local nums = KEYS[2]
local stock = redis.call('GET', good)
if not stock or tonumber(stock) <= 0 then
    return -1
end
if tonumber(stock) - tonumber(nums) <0 then
    return -1
end
redis.call('incrBy',good,0 - tonumber(nums))
return 1