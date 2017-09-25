package com.example.androidca;

import org.androidtransfuse.annotations.TransfuseModule;
import org.androidtransfuse.annotations.UsesPermission;
import org.androidtransfuse.annotations.UsesPermissions;

/**
 * @author John Ericksen
 */
@TransfuseModule
@UsesPermissions({
        @UsesPermission(name = "android.permission.INTERNET"),
        @UsesPermission(name = "android.permission.ACCESS_NETWORK_STATE")
})
public class Module {
}
