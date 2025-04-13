#pragma once
#include "World.h"

class World;

class Organism
{
public:
    enum class AllOrganisms
    {
        HUMAN, // i'm only human after all
        WOLF,
        SHEEP,
        FOX,
        TURTLE,
        ANTELOPE,
        GRASS,
        DANDELION, // mlecz
        GUARANA,
        NIGHTSHADE, // wilcze jagody
        PINEBORSCHT // barszcz sosnowskiego
    };
    virtual void takeAction() = 0;
    virtual void colision(Organism *other);
    virtual bool specialAbility(Organism *other); // dla wszytkich organizmów inych od człowieka
    void drawOrg();
    void setStrength(int str);
    void setInitiative(int ini);
    void setPos(int x, int y);
    void setAge(int newAge);
    void die();
    char getSymbol() const;
    int getXPos() const;
    int getYPos() const;
    int getPrevXPos() const;
    int getPrevYPos() const;
    int getInitiative() const;
    int getStrength() const;
    int getAge() const;
    std::string getTypeString() const;
    AllOrganisms getType() const;
    bool checkIfIsAlive() const;
    virtual bool isAnAnimal() const;
    Organism(World &wR, const int &xPos, const int &yPos, const char &symbol, const int &strength, const int &initiative, AllOrganisms type);
    ~Organism();
    
protected:
    int strength;
    int initiative;
    char symbol;
    int xPos;
    int yPos;
    int prevX;
    int prevY;
    int age = 1;
    bool isAlive = true;
    AllOrganisms type;
    World &worldRef;
    std::string typeS;
};
