
#include <string.h>
#include <jni.h>


JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_OneData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "http://cricpro.cricnet.co.in/api/values/");
}
JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_TwoData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "http://cricnet.co.in/ManagePlaying/PlayerImage/");
}
JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_ThreeData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "http://cricnet.co.in/ManagePlaying/TeamImages/");
}

JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_FourData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "https://api.storelyapp.com:5000/v1/getGames/");
}
JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_FiveData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "https://newsapi.org/v2/everything");
}

JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_SixData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "livescore6.p.rapidapi.com");
}

JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_SevenData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "d8dd056a73msh8a5ffee0891991dp1ddae9jsna9f688347177");
}

JNIEXPORT jstring JNICALL
Java_com_livescorescrickets_livescores_adsimp_EightData(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "https://newsapi.org/v2/");
}


