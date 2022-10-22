package JavaCode.Clib;//package Clib;
//
//import android.content.res.AssetFileDescriptor;
//import android.media.MediaPlayer;
//import android.media.SoundPool;
//import com.silvershield.onepiece.GameMidlet;
//import java.io.IOException;
//
//public class mSound {
//    public static int CurMusic = -1;
//    private static final int MAX_VOLUME = 10;
//    public static int idCurent = -1;
//    public static boolean isMusic = true;
//    public static boolean isSound = true;
//    public static MediaPlayer[] music;
//    public static SoundPool[] sound;
//    public static int[] soundID;
//    public static float volumeMusic = 0.8f;
//    public static float volumeSound = 0.7f;
//
//    public static void init(int sizeMusic, int sizeSound) {
//        music = new MediaPlayer[sizeMusic];
//        for (int i = 0; i < music.length; i++) {
//            music[i] = getMediaSoundFile(new StringBuilder(String.valueOf(i)).toString());
//        }
//        soundID = new int[sizeSound];
//        sound = new SoundPool[sizeSound];
//        for (int i2 = 0; i2 < sound.length; i2++) {
//            sound[i2] = new SoundPool(1, 3, 0);
//            sound[i2].setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//                /* class Clib.mSound.AnonymousClass1 */
//
//                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                }
//            });
//            soundID[i2] = getSoundPoolSource(i2, new StringBuilder(String.valueOf(i2)).toString());
//        }
//        System.gc();
//    }
//
//    public static int getSoundPoolSource(int index, String fileName) {
//        try {
//            AssetFileDescriptor descriptor = GameMidlet.asset.openFd("sound/s" + fileName + ".wav");
//            return sound[index].load(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength(), 0);
//        } catch (IOException e) {
//            System.out.println("tieng dong loi: " + fileName);
//            return -1;
//        }
//    }
//
//    public static MediaPlayer getMediaSoundFile(String fileName) {
//        MediaPlayer m = new MediaPlayer();
//        try {
//            AssetFileDescriptor descriptor = GameMidlet.asset.openFd("sound/m" + fileName + ".mp3");
//            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
//            m.prepare();
//        } catch (IOException e) {
//            System.out.println("am thanh loi: " + fileName);
//            e.printStackTrace();
//        } finally {
//            System.gc();
//        }
//        return m;
//    }
//
//    public static void playSound(int id, float volume) {
//        if (isSound && GameMidlet.instance.getVolum() > 0 && sound != null && id < sound.length) {
//            try {
//                sound[id].play(soundID[id], volume, volume, 0, 0, 1.0f);
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void playSound(int id, float volume, int loop) {
//        if (isSound && GameMidlet.instance.getVolum() > 0 && sound != null && sound[id] != null) {
//            try {
//                sound[id].play(soundID[id], volume, volume, 0, 0, 1.0f);
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void SetLoopSound(int id, float volume, int loop) {
//        if (!isSound || sound == null) {
//            return;
//        }
//        if (loop == 0) {
//            sound[id].play(soundID[id], 0.0f, 0.0f, 0, 0, 1.0f);
//        } else {
//            sound[id].play(soundID[id], volume, volume, 0, loop, 1.0f);
//        }
//    }
//
//    public static void UnSetLoopAll() {
//        if (isSound && sound != null) {
//            for (int i = 0; i < sound.length; i++) {
//                try {
//                    sound[i].play(soundID[i], 0.0f, 0.0f, 0, 0, 1.0f);
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void playMus(int id, float volume, boolean isLoop) {
//        if (isMusic) {
//            if (music != null) {
//                for (int i = 0; i < music.length; i++) {
//                    if (!(music[i] == null || !music[i].isPlaying() || i == id)) {
//                        music[i].pause();
//                    }
//                }
//            }
//            if (id >= 0 && id <= music.length - 1) {
//                try {
//                    music[id].setVolume(volume, volume);
//                    music[id].setLooping(isLoop);
//                    if (!music[id].isPlaying()) {
//                        music[id].seekTo(0);
//                        music[id].start();
//                    }
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void stopAll() {
//        stopSoundAll();
//        stopMusicAll();
//    }
//
//    private static void stopMusicAll() {
//        for (int i = 0; i < music.length; i++) {
//            if (music[i].isPlaying()) {
//                music[i].stop();
//            }
//        }
//    }
//
//    public static void pauseCurMusic() {
//        for (int i = 0; i < music.length; i++) {
//            if (music[i].isPlaying()) {
//                music[i].pause();
//                CurMusic = i;
//            }
//        }
//    }
//
//    public static void stopSoundAll() {
//        if (sound != null) {
//            for (int i = 0; i < sound.length; i++) {
//                if (sound[i] != null) {
//                    sound[i].stop(soundID[i]);
//                }
//            }
//        }
//        System.gc();
//    }
//
//    public static void setVolume(int id, int index, int soundVolume) {
//        float volume = (float) (1.0d - (Math.log((double) (10 - soundVolume)) / Math.log(10.0d)));
//        if (music[id] != null) {
//            music[id].setVolume(volume, volume);
//        }
//    }
//}