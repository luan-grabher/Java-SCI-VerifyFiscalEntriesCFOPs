select I.BDCHAVE, I.BDCODNAT from VEF_EMP_TMOV:entriesTypeITENS I
INNER JOIN VEF_EMP_TMOV:entriesType M
ON M.BDCODEMP = I.BDCODEMP AND M.BDCHAVE = I.BDCHAVE
WHERE
I.BDCODEMP = :enterpriseCode
AND M.BDREFLAN >= :referenceStart
AND M.BDREFLAN <= :referenceEnd
AND M.BDCODTER = :participant
AND NOT I.BDCODNAT IN (:participantCfopsInList)