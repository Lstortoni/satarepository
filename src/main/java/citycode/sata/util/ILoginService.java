package citycode.sata.util;

import citycode.sata.dto.request.AuthRequestDTOReq;
import citycode.sata.dto.response.AuthResponseDTORes;

public interface ILoginService {

    AuthResponseDTORes authenticate(AuthRequestDTOReq request);
}
