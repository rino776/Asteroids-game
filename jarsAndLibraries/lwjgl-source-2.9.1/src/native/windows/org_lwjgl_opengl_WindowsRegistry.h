/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_lwjgl_opengl_WindowsRegistry */

#ifndef _Included_org_lwjgl_opengl_WindowsRegistry
#define _Included_org_lwjgl_opengl_WindowsRegistry
#ifdef __cplusplus
extern "C" {
#endif
#undef org_lwjgl_opengl_WindowsRegistry_HKEY_CLASSES_ROOT
#define org_lwjgl_opengl_WindowsRegistry_HKEY_CLASSES_ROOT 1L
#undef org_lwjgl_opengl_WindowsRegistry_HKEY_CURRENT_USER
#define org_lwjgl_opengl_WindowsRegistry_HKEY_CURRENT_USER 2L
#undef org_lwjgl_opengl_WindowsRegistry_HKEY_LOCAL_MACHINE
#define org_lwjgl_opengl_WindowsRegistry_HKEY_LOCAL_MACHINE 3L
#undef org_lwjgl_opengl_WindowsRegistry_HKEY_USERS
#define org_lwjgl_opengl_WindowsRegistry_HKEY_USERS 4L
/*
 * Class:     org_lwjgl_opengl_WindowsRegistry
 * Method:    nQueryRegistrationKey
 * Signature: (ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_WindowsRegistry_nQueryRegistrationKey
  (JNIEnv *, jclass, jint, jstring, jstring);

#ifdef __cplusplus
}
#endif
#endif